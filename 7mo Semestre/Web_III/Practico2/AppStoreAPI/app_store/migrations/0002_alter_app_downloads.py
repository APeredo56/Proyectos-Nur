# Generated by Django 5.0.4 on 2024-04-23 20:02

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('app_store', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='app',
            name='downloads',
            field=models.IntegerField(default=0),
        ),
    ]