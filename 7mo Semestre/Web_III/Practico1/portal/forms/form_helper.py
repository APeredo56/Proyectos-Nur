from django import forms

text_form_control = forms.TextInput(
    attrs={
        'class': 'form-control'
    }
)
number_form_control = forms.NumberInput(
    attrs={
        'class': 'form-control'
    }
)
password_form_control = forms.PasswordInput(
    attrs={
        'class': 'form-control'
    }
)
image_form_control = forms.FileInput(
    attrs={
        'class': 'form-control image-input'
    },
)

