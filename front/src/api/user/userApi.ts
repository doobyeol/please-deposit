import type { User } from "@/models/user/User";
import { callPost } from "../http";

const login = async (accessToken: string): Promise<User> => {
  const data: User = await callPost("/api/login/token/access", {
    accessToken,
  });
  return data;
};

const loginByRefreshToken = async (refreshToken: string): Promise<User> => {
  const data: User = await callPost("/api/login/token/refresh", {
    refreshToken,
  });
  return data;
};

export { login, loginByRefreshToken };
