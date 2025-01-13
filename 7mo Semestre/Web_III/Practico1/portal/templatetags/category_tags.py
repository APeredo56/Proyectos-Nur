from django import template
from portal.models import Category

register = template.Library()


@register.inclusion_tag("category/nav_category_list.html")
def category_list():
    categories = Category.objects.all()
    return {'categories': categories}
