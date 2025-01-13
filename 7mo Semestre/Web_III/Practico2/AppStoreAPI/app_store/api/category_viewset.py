from rest_framework import serializers, viewsets

from app_store.api import AppSerializer
from app_store.models import Category


class CategorySerializer(serializers.ModelSerializer):
    apps = AppSerializer(many=True, read_only=True)

    class Meta:
        model = Category
        fields = '__all__'


class CategoryViewSet(viewsets.ModelViewSet):
    queryset = Category.objects.all()
    serializer_class = CategorySerializer
