from django.contrib.auth import authenticate
from django.contrib.auth.models import User
from django.shortcuts import redirect, render

from universidad.forms import LoginForm, RegisterForm


def auth_login(request):
    form = LoginForm()
    if request.method == 'POST':
        form = LoginForm(request.POST)
        if form.is_valid():
            username = form.cleaned_data.get('username')
            password = form.cleaned_data.get('password')
            authenticated_user = authenticate(
                username=username,
                password=password
            )
            if authenticated_user is not None:
                request.session['username'] = username
                return redirect('materia_list')
        return render(
            request,
            'universidad/auth/login.html',
            {'form': form}
        )
    return render(
        request,
        'universidad/auth/login.html',
        {'form': form}
    )


def auth_logout(request):
    del request.session['username']
    return redirect('auth_login')


def auth_register(request):
    if 'username' in request.session:
        return redirect('materia_list')
    form = RegisterForm()
    if request.method == 'POST':
        form = RegisterForm(request.POST)
        if form.is_valid():
            User.objects.create_user(
                username=form.cleaned_data.get('username'),
                password=form.cleaned_data.get('password'),
                first_name=form.cleaned_data.get('first_name'),
                last_name=form.cleaned_data.get('last_name'),
                email=form.cleaned_data.get('email')
            )
            return redirect('auth_login')
        return render(
            request,
            'universidad/auth/register.html',
            {'form': form}
        )
    return render(request, 'universidad/auth/register.html', {'form': form})