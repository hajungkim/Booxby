
const routes = [
  {
    path: '/',
    component: () => import('pages/intro.vue')
  },
  {
    path: '/',
    component: () => import('layouts/CommonBox.vue'),
    redirect: 'login',
    children: [
      { path: 'login', component: () => import('pages/Login.vue')},
      { path: 'hashtag', component: () => import('pages/Hashtag.vue')},
      { path: 'worldCup', component: () => import('pages/Worldcup.vue')},
      { path: 'main', component: () => import('pages/Main.vue')},
      { path: 'detail', component: () => import('pages/Detail.vue')},
      { path: 'signup', component: () => import('pages/Signup.vue') },
      { path: 'modify', component: () => import('pages/Modify.vue') },
      { path: 'my', component: () => import('components/Mypage.vue') },
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
