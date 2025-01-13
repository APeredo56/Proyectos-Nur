from django import forms
from django.contrib.auth.models import User

from universidad.forms.form_helper import text_form_control, password_form_control, email_form_control


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
    first_name = forms.CharField(
        label="Nombre",
        max_length=100,
        widget=text_form_control
    )
    last_name = forms.CharField(
        label="Apellido",
        max_length=100,
        widget=text_form_control
    )
    email = forms.EmailField(
        label="Correo electrónico",
        widget=email_form_control
    )

    def clean_username(self):
        username = self.cleaned_data['username']
        if User.objects.filter(username=username).exists():
            raise forms.ValidationError('El usuario ya existe')
        return username

    def clean_email(self):
        email = self.cleaned_data['email']
        if User.objects.filter(email=email).exists():
            raise forms.ValidationError('El Email ya existe')
        return email
