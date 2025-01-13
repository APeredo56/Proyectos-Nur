from django.db.models import Avg
from django.shortcuts import render

from portal.models import Category, Rating, Game


# Create your views here.


def index(request):
    categories = Category.objects.all()
    top_games = Game.objects.annotate(average_rating=Avg('ratings__score')).order_by('-average_rating')[:5]
    return render(request, 'index.html', {
        'categories': categories,
        'top_games': top_games
    })
