import { createRouter, createWebHistory } from "vue-router";
import { useViewStore } from "@/stores/common/viewStore";
import LoginView from "@/views/LoginView.vue";
import EntryView from "@/views/EntryView.vue";
import ExpenseView from "@/views/ExpenseView.vue";
import AboutView from "@/views/AboutView.vue";
import { useSessionStore } from "@/stores/common/sessionStore";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "login",
      component: LoginView,
      meta: { requiredLogin: false, showNavBar: false, showMenuBar: false },
    },
    {
      path: "/entry",
      name: "entry",
      component: EntryView,
      meta: { requiredLogin: true, showNavBar: false, showMenuBar: false },
    },
    {
      path: "/expense",
      name: "expense",
      component: ExpenseView,
      meta: { requiredLogin: true, showNavBar: true, showMenuBar: true },
    },
    {
      path: "/about",
      name: "about",
      component: AboutView,
      meta: { requiredLogin: true, showNavBar: true, showMenuBar: true },
    },
  ],
});

router.beforeEach(async (to, from, next) => {
  const { setShowNavBar, setShowMenuBar } = useViewStore();
  setShowNavBar(to.meta.showNavBar as boolean);
  setShowMenuBar(to.meta.showMenuBar as boolean);

  const { isLoggedIn, login } = useSessionStore();
  if (to.meta.requiredLogin && !isLoggedIn) {
    const loginResult = await login();
    if (loginResult) {
      next();
    } else {
      next({ path: "/" });
    }
  } else {
    next();
  }
});

export default router;
