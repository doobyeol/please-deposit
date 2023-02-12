import type { ApiError } from "@/api/http";
import { login, loginByRefreshToken } from "@/api/user/userApi";
import type { User } from "@/models/user/User";
import Cookies from "js-cookie";
import { defineStore } from "pinia";

export interface SessionState {
  user?: User | null;
  isLoggedIn: boolean;
}

export const useSessionStore = defineStore("sessionStore", {
  state: () =>
    ({
      user: null,
      isLoggedIn: false,
    } as SessionState),
  getters: {},
  actions: {
    async login(): Promise<boolean> {
      const accessToken: string | undefined = Cookies.get("acstk");
      if (!accessToken) {
        return false;
      }

      try {
        this.user = await login(accessToken);
        this.isLoggedIn = true;
        return true;
      } catch (error: any) {
        const apiError = error as ApiError;
        const errorMessage: string = apiError.message || "login error";
        console.warn(errorMessage);

        const refreshToken: string | undefined = Cookies.get("rftk");
        if (!refreshToken) {
          return false;
        }
        return this.loginByRefreshToken(refreshToken);
      }
    },
    async loginByRefreshToken(refreshToken: string): Promise<boolean> {
      try {
        this.user = await loginByRefreshToken(refreshToken);
        this.isLoggedIn = true;
        return true;
      } catch (error: any) {
        const apiError = error as ApiError;
        const errorMessage: string =
          apiError.message || "login by refreshToken error";
        console.warn(errorMessage);
        return false;
      }
    },
  },
});
