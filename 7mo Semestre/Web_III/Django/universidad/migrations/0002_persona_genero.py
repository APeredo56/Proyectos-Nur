# Generated by Django 4.2.11 on 2024-03-15 18:49

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('universidad', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='persona',
            name='genero',
            field=models.IntegerField(choices=[(1, 'Masculino'), (0, 'Femenino'), (-1, 'Otro')], default=-1),
            preserve_default=False,
        ),
    ]