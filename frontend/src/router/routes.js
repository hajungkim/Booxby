
const routes = [
  {
    path: '/',
    component: () => import('layouts/CommonBox.vue'),
    children: [
      { path: '', component: () => import('pages/Login.vue') },
      { path: 'signUp', component: () => import('pages/Signup.vue') }
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
