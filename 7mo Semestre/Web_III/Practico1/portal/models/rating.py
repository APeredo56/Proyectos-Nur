from django.db import models


class Rating(models.Model):
    score = models.IntegerField()
    game = models.ForeignKey('Game', on_delete=models.CASCADE, related_name='ratings')
    user = models.ForeignKey('User', on_delete=models.CASCADE)

    def __str__(self):
        return str(self.score)
