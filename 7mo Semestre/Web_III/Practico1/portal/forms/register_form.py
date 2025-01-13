from django import forms
from django.contrib.auth.models import User

from portal.forms.form_helper import text_form_control, password_form_control


class RegisterForm(forms.Form):
    username = forms.CharField(
        label="Nombre de usuario",
        max_length=50,
        widget=text_form_control
    )
    password = forms.CharField(
        label="Contraseña",
        widget=password_form_control
    )

    def clean_username(self):
        username = self.cleaned_data['username']
        if User.objects.filter(username=username).exists():
            raise forms.ValidationError('El usuario ya existe')
        return username
