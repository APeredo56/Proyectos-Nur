from django.urls import  path
from portal import views

urlpatterns = [
    path("", views.index, name="index"),
    path("category/<int:category_id>/", views.category_detail, name="category_detail"),
    path("category/create/", views.category_create, name="category_create"),
    path("category/edit/<int:category_id>/", views.category_edit, name="category_edit"),
    path("category/delete/<int:category_id>/", views.category_delete, name="category_delete"),
    path("game/<int:game_id>/", views.game_detail, name="game_detail"),
    path("game/<int:game_id>/rate/", views.rate_game, name="rate_game"),
    path("game/create/", views.game_create, name="game_create"),
    path("game/edit/<int:game_id>/", views.game_edit, name="game_edit"),
    path("game/delete/<int:game_id>/", views.game_delete, name="game_delete"),
    path("login/", views.auth_login, name="login"),
    path("logout/", views.auth_logout, name="logout"),
    path("register/", views.auth_register, name="register"),
]