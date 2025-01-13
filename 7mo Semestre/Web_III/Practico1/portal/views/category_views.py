from django.shortcuts import render, redirect

from portal.forms import CategoryForm
from portal.models import Category


def category_detail(request, category_id):
    category = Category.objects.get(pk=category_id)
    return render(request, 'category/category_detail.html',
                  {'category_title': category.title,
                   'games': category.games.all()})


def category_create(request):
    form = CategoryForm()
    if request.method == 'POST':
        form = CategoryForm(request.POST, request.FILES)
        if form.is_valid():
            form.save()
            return redirect("index")
    return render(request, "category/form.html", {"form": form})


def category_edit(request, category_id):
    category = Category.objects.get(id=category_id)
    if category is None:
        return redirect("index")
    category_image = category.image
    if request.method == 'POST':
        form = CategoryForm(request.POST, request.FILES, instance=category)
        if form.is_valid():
            form.save()
            return redirect("index")
    else:
        form = CategoryForm(instance=category)

    return render(request, "category/form.html", {
        "form": form,
        "category_image": category_image,
        "edit": True
    })


def category_delete(request, category_id):
    # if request.method == 'GET':
    #     return redirect("index")
    category = Category.objects.get(id=category_id)
    category.delete()
    return redirect("index")
