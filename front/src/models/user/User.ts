export enum LoginType {
  KAKAO,
}

export interface User {
  userSeq?: number;
  userId?: string;
  nickname?: string;
  loginType?: LoginType;
}
