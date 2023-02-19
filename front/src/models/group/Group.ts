export enum UserGroupStatus {
  WAITING = "WAITING",
  ACCEPTED = "ACCEPTED",
  REJECTED = "REJECTED",
}

export interface Group {
  groupId?: number;
  groupName?: string;
  groupOwner?: string;
  memberCount?: number;
  createdAt?: string;
  status?: UserGroupStatus;
}
