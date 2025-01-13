export const Routes = {
    HOME: '/',
    USER: {
        LIST: '/users',
        CREATE: '/users/create',
    },
    TASK: {
        LIST: '/tasks',
        CREATE: '/tasks/create',
        EDIT: '/tasks/edit/:id',
        EDIT_PARAM: (id?: number) => `/tasks/edit/${id}`,
        DETAIL: '/tasks/detail/:id',
        DETAIL_PARAM: (id?: number) => `/tasks/detail/${id}`,
    },
}