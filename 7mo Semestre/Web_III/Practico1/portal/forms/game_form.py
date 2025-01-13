from django import forms

from portal.forms.form_helper import text_form_control, image_form_control
from portal.models import Game


class GameForm(forms.ModelForm):
    title = forms.CharField(
        label="Titulo",
        max_length=50,
        widget=text_form_control,
    )
    image = forms.FileField(
        label="Imagen",
        required=False,
        widget=image_form_control
    )

    class Meta:
        model = Game
        fields = ("title", "image", "categories")
        widgets = {
            'categories': forms.CheckboxSelectMultiple(attrs={'class': 'form-check-input'}),
        }

