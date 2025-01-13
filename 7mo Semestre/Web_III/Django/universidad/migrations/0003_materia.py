# Generated by Django 4.2.11 on 2024-03-19 18:22

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('universidad', '0002_persona_genero'),
    ]

    operations = [
        migrations.CreateModel(
            name='Materia',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre', models.CharField(max_length=50)),
                ('creditos', models.IntegerField()),
                ('horario', models.CharField(max_length=100)),
                ('docente', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='materias', to='universidad.persona')),
            ],
        ),
    ]