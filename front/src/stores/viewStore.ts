import { defineStore } from "pinia";

export type ViewState = {
  isShowNavBar: Boolean;
  isShowMenuBar: Boolean;
};

export const useViewStore = defineStore("viewStore", {
  state: () =>
    ({
      isShowNavBar: false,
      isShowMenuBar: false,
    } as ViewState),
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
