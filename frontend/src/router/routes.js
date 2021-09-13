
const routes = [
  {
    path: '/',
    component: () => import('layouts/CommonBox.vue'),
    children: [
      { path: 'signup', component: () => import('pages/Signup.vue') },
      { path: 'modify', component: () => import('pages/Modify.vue') },
      { path: 'my', component: () => import('components/Mypage.vue') }
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
