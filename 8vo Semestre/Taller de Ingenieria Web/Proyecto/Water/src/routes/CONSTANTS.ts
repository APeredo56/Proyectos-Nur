export const Routes = {
    HOME: '/',
    LOGIN: '/login',
    DEPARTMENT: {
        LIST: '/departments',
        DETAIL: '/departments/:id',
        DETAIL_PARAM: (id?: number) => `/departments/${id}`,
        CREATE: '/departments/create',
        EDIT: '/departments/edit/:id',
        EDIT_PARAM: (id?: number) => `/departments/edit/${id}`,
    },
    USER: {
        LIST: '/users',
        DETAIL: '/users/:id',
        DETAIL_PARAM: (id?: number) => `/users/${id}`,
        CREATE: '/users/create',
        EDIT: '/users/edit/:id',
        EDIT_PARAM: (id?: number) => `/users/edit/${id}`,
        RANKING: '/users/ranking',
    },
    MUNICIPALITY: {
        LIST: '/municipalities',
        DETAIL: '/municipalities/:id',
        DETAIL_PARAM: (id?: number) => `/municipalities/${id}`,
        CREATE: '/municipalities/create',
        EDIT: '/municipalities/edit/:id',
        EDIT_PARAM: (id?: number) => `/municipalities/edit/${id}`,
    },
    COMMUNITY: {
        LIST: '/communities',
        DETAIL: '/communities/:id',
        DETAIL_PARAM: (id?: number) => `/communities/${id}`,
        CREATE: '/communities/create',
        EDIT: '/communities/edit/:id',
        EDIT_PARAM: (id?: number) => `/communities/edit/${id}`,
    },
    WATERBODY: {
        LIST: '/water-bodies',
        DETAIL: '/water-bodies/:id',
        DETAIL_PARAM: (id?: number) => `/water-bodies/${id}`,
        CREATE: '/water-bodies/create',
        EDIT: '/water-bodies/edit/:id',
        EDIT_PARAM: (id?: number) => `/water-bodies/edit/${id}`,
    },
    FIELDTRIP: {
        LIST: '/field-trips',
        DETAIL: '/field-trips/:id',
        DETAIL_PARAM: (id?: number) => `/field-trips/${id}`,
        CREATE: '/field-trips/create',
        EDIT: '/field-trips/edit/:id',
        EDIT_PARAM: (id?: number) => `/field-trips/edit/${id}`,
        ASSIGNMENTS: '/field-trips/:id/assignments',
        ASSIGNMENTS_PARAM: (id?: number) => `/field-trips/${id}/assignments`,
        OWN: '/field-trips/own',
        ANALYSES: '/field-trips/:fieldTripId/analyses/user',
        ANALYSES_PARAM: (fieldTripId?: number) => `/field-trips/${fieldTripId}/analyses/user`,
    },
    ANALYSIS: {
        LIST: '/analyses',
        DETAIL: '/analyses/:id',
        DETAIL_PARAM: (id?: number) => `/analyses/${id}`,
        CREATE: '/analyses/create',
        EDIT: '/analyses/edit/:id',
        EDIT_PARAM: (id?: number) => `/analyses/edit/${id}`,
    },
    MEDAL: {
        MY_MEDALS: '/my-medals',
    },
}