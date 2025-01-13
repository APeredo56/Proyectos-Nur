from django.db.models import Avg
from django.shortcuts import render, redirect

from portal.forms import GameForm
from portal.models import Game, Rating


def game_detail(request, game_id):
    game = Game.objects.get(pk=game_id)
    ratings = Rating.objects.filter(game_id=game_id)
    average_rating = game.ratings.aggregate(avg_rating=Avg('score'))['avg_rating']
    user_rating = None
    user_id = request.session.get('user_id')
    if user_id is not None:
        user_rating = Rating.objects.filter(
            user_id=user_id,
            game_id=game_id
        ).first()
    return render(request, 'game/game_detail.html', {
        'game': game,
        'ratings': ratings,
        'average_rating': average_rating,
        'user_rating': user_rating
    })


def rate_game(request, game_id):
    user_id = request.session.get('user_id')
    if user_id is None:
        return redirect('auth_login')
    rating = Rating.objects.filter(
        user_id=user_id,
        game_id=game_id
    ).first()
    if rating is None:
        rating = Rating()
        rating.user_id = user_id
        rating.game_id = game_id
    rating.score = request.POST.get('score')
    rating.save()
    return redirect('game_detail', game_id=game_id)


def game_create(request):
    form = GameForm()
    if request.method == 'POST':
        form = GameForm(request.POST, request.FILES)
        if form.is_valid():
            form.save()
            return redirect("index")
    return render(request, "game/form.html", {"form": form})


def game_edit(request, game_id):
    game = Game.objects.get(id=game_id)
    if game is None:
        return redirect("index")
    game_image = game.image
    if request.method == 'POST':
        form = GameForm(request.POST, request.FILES, instance=game)
        if form.is_valid():
            form.save()
            return redirect("index")
    else:
        form = GameForm(instance=game)

    return render(request, "game/form.html", {
        "form": form,
        "game_image": game_image,
        "edit": True
    })


def game_delete(request, game_id):
    # if request.method == 'GET':
    #     return redirect("index")
    game = Game.objects.get(id=game_id)
    game.delete()
    return redirect("index")


