import { createRouter, createWebHistory } from "vue-router";
import { useViewStore } from "@/stores/viewStore";
import LoginView from "@/views/LoginView.vue";
import EntryView from "@/views/EntryView.vue";
import HomeView from "@/views/HomeView.vue";
import AboutView from "@/views/AboutView.vue";
import { useSessionStore } from "@/stores/sessionStore";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "login",
      component: LoginView,
      meta: { showNavBar: false },
    },
    {
      path: "/entry",
      name: "entry",
      component: EntryView,
      meta: { showNavBar: true },
    },
    {
      path: "/main",
      name: "home",
      component: HomeView,
      meta: { showNavBar: true },
    },
    {
      path: "/about",
      name: "about",
      component: AboutView,
      meta: { showNavBar: true },
    },
  ],
});

router.beforeEach((to, from, next) => {
  const { isLoggedIn } = useSessionStore();
  const { showMenuBar, showNavBar, hideMenuBar, hideNavBar } = useViewStore();
  if (to.meta.showNavBar) {
    showNavBar();
    showMenuBar();
  } else {
    hideNavBar();
    hideMenuBar();
  }

  // if (isLoggedIn) const accessToken = Cookies.get("acstk");

  next();
});

export default router;
