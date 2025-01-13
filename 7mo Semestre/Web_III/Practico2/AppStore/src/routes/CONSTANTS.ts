export const Routes = {
    HOME: '/',
    ADMIN: '/admin',
    APPS: {
        LIST: '/apps',
        CREATE: '/apps/create',
        DETAIL: '/apps/:id',
        DETAIL_PARAM: (id?: number) => `/apps/${id}`,
        EDIT: '/apps/edit/:id',
        EDIT_PARAM: (id?: number) => `/apps/edit/${id}`
    },
    CATEGORIES: {
        LIST: '/categories',
        CREATE: '/categories/create',
        DETAIL: '/categories/:id',
        DETAIL_PARAM: (id?: number) => `/categories/${id}`,
        EDIT: '/categories/edit/:id',
        EDIT_PARAM: (id?: number) => `/categories/edit/${id}`
    }
}