from django.shortcuts import redirect, render

from portal.forms import LoginForm, RegisterForm
from portal.models import User


def auth_login(request):
    if 'username' in request.session:
        return redirect('index')
    form = LoginForm()
    if request.method == 'POST':
        form = LoginForm(request.POST)
        if form.is_valid():
            username = form.cleaned_data.get('username')
            password = form.cleaned_data.get('password')
            try:
                authenticated_user = User.objects.get(username=username, password=password)
            except User.DoesNotExist:
                authenticated_user = None
            if authenticated_user is not None:
                if username == "admin" and password == "admin":
                    request.session['is_admin'] = True
                request.session['username'] = username
                request.session['user_id'] = authenticated_user.id
                return redirect('index')
    return render(
        request,
        'auth/login.html',
        {'form': form}
    )


def auth_logout(request):
    del request.session['username']
    del request.session['user_id']
    try:
        del request.session['is_admin']
    except KeyError:
        pass
    return redirect('index')


def auth_register(request):
    if 'username' in request.session:
        return redirect('index')
    form = RegisterForm()
    if request.method == 'POST':
        form = RegisterForm(request.POST)
        if form.is_valid():
            User.objects.create(
                username=form.cleaned_data.get('username'),
                password=form.cleaned_data.get('password'),
            )
            return redirect('login')
    return render(request, 'auth/register.html', {'form': form})
