from django.db import models


class Screenshot(models.Model):
    image = models.ImageField(upload_to='static/img/screenshots/')
    app = models.ForeignKey('App', on_delete=models.CASCADE, related_name='screenshots')

    def __str__(self):
        return self.image.url
