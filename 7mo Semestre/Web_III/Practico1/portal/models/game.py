from django.db import models


class Game(models.Model):
    title = models.CharField(max_length=100)
    image = models.ImageField(null=True, blank=True, upload_to='static/img/games/')
    categories = models.ManyToManyField("Category", related_name="games")

    def __str__(self):
        return self.title