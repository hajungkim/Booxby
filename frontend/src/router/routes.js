
const routes = [
  {
    path: '/',
    component: () => import('layouts/CommonBox.vue'),
    children: [
      { path: '', component: () => import('pages/Login.vue') },
      { path: 'signUp', component: () => import('pages/Signup.vue') },
      { path: 'hashtag', component: () => import('pages/Hashtag.vue')},
      { path: 'worldCup', component: () => import('pages/Worldcup.vue')},
      { path: 'main', component: () => import('pages/Main.vue')},
      { path: 'detail', component: () => import('pages/Detail.vue')}
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
