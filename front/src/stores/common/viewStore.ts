import { defineStore } from "pinia";

export const useViewStore = defineStore("viewStore", {
  state: () => ({
    isShowNavBar: false,
    isShowMenuBar: false,
  }),
  getters: {},
  actions: {
    setShowNavBar(show: boolean) {
      this.isShowNavBar = show;
    },
    setShowMenuBar(show: boolean) {
      this.isShowMenuBar = show;
    },
  },
});
