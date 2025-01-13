from django.db import models


class App(models.Model):
    name = models.CharField(max_length=100)
    description = models.TextField()
    icon = models.ImageField(upload_to='static/img/icons/', blank=True)
    cover_photo = models.ImageField(upload_to='static/img/covers/', blank=True)
    price = models.FloatField()
    downloads = models.IntegerField(default=0)
    release_date = models.DateField(auto_now_add=True)

    def __str__(self):
        return self.name
