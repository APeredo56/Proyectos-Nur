from rest_framework import serializers, viewsets
from rest_framework.decorators import action
from rest_framework.response import Response

from app_store.api.simple_serializers import SimpleCategorySerializer
from app_store.api.screenshot_viewset import ScreenshotSerializer
from app_store.models import App
from app_store.models import Category
from app_store.models import Screenshot


class AppSerializer(serializers.ModelSerializer):
    categories = SimpleCategorySerializer(many=True, read_only=True)
    screenshots = ScreenshotSerializer(many=True, read_only=True)
    screenshots_files = serializers.ListField(child=serializers.ImageField(), write_only=True, required=False)
    category_ids = serializers.PrimaryKeyRelatedField(
        queryset=Category.objects.all(), source='categories', write_only=True, many=True
    )

    class Meta:
        model = App
        fields = '__all__'

    def validate(self, data):
        required_message = "This field is required."
        if not self.instance:
            if 'icon' not in data:
                raise serializers.ValidationError({
                    "icon": required_message
                })
            if 'cover_photo' not in data:
                raise serializers.ValidationError({
                    "cover_photo": required_message
                })
            if 'screenshots_files' not in data:
                raise serializers.ValidationError({
                    "screenshots_files": required_message
                })
        return data
    
    def validate_category_ids(self, value):
        if not value:
            raise serializers.ValidationError("This field is required.")
        return value
    
    def create(self, validated_data):
        screenshots_data = validated_data.pop('screenshots_files')
        categories = validated_data.pop('categories', None)
        app = App.objects.create(**validated_data)

        if categories:
            app.categories.set(categories)

        for screenshot_data in screenshots_data:
            Screenshot.objects.create(app=app, image=screenshot_data)

        return app
    
    def update(self, instance, validated_data):
        screenshots_data = validated_data.pop('screenshots_files', None)
        categories = validated_data.pop('categories', None)
        
        instance = super().update(instance, validated_data)

        if categories:
            instance.categories.set(categories)

        if screenshots_data:
            for screenshot_data in screenshots_data:
                Screenshot.objects.create(app=instance, image=screenshot_data)

        return instance


class AppViewSet(viewsets.ModelViewSet):
    queryset = App.objects.all()
    serializer_class = AppSerializer

    @action(detail=False, methods=['get'], url_path='latest')
    def latest(self, request, pk=None):
        latest_apps = App.objects.all().order_by('-release_date')[:20]
        serializer = AppSerializer(latest_apps, many=True)
        return Response(serializer.data)

    @action(detail=False, methods=['get'], url_path='top-free', url_name='top_free')
    def top_free(self, request, pk=None):
        top = App.objects.filter(price=0).order_by('-downloads')[:20]
        serializer = AppSerializer(top, many=True)
        return Response(serializer.data)

    @action(detail=False, methods=['get'], url_path='top-paid', url_name='top_paid')
    def top_paid(self, request, pk=None):
        top = App.objects.filter(price__gt=0).order_by('-downloads')[:20]
        serializer = AppSerializer(top, many=True)
        return Response(serializer.data)
    
    @action(detail=True, methods=['post'], url_path='download', url_name='download')
    def download(self, request, pk=None):
        app = self.get_object()
        app.downloads += 1
        app.save()
        return Response({'downloads': app.downloads})
