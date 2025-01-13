from django.db import models

from portal.models import Game


class Category(models.Model):
    title = models.CharField(max_length=100)
    image = models.ImageField(null=True, blank=True, upload_to='static/img/categories/')

    def __str__(self):
        return self.title
