import { defineStore } from "pinia";

export const useViewStore = defineStore("viewStore", {
  state: () => ({
    isShowNavBar: false,
    isShowMenuBar: false,
  }),
  getters: {},
  actions: {
    showNavBar() {
      this.isShowNavBar = true;
    },
    hideNavBar() {
      this.isShowNavBar = false;
    },
    showMenuBar() {
      this.isShowMenuBar = true;
    },
    hideMenuBar() {
      this.isShowMenuBar = false;
    },
  },
});
