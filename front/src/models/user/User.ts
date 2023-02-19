export enum LoginType {
  KAKAO = "KAKAO",
}

export interface User {
  userSeq?: number;
  userId?: string;
  nickname?: string;
  loginType?: LoginType;
}
