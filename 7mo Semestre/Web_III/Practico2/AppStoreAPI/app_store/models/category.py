from django.db import models


class Category(models.Model):
    name = models.CharField(max_length=100)
    apps = models.ManyToManyField('App', related_name='categories')

    def __str__(self):
        return self.name
