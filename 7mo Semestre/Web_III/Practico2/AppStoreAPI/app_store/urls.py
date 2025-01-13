from django.urls import path, include
from rest_framework import routers

from app_store.api import AppViewSet, CategoryViewSet, ScreenshotViewSet

router = routers.DefaultRouter()
router.register(r'apps', AppViewSet)
router.register(r'categories', CategoryViewSet)
router.register(r'screenshots', ScreenshotViewSet)

urlpatterns = [
    path('', include(router.urls)),
]