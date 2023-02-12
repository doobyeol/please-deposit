export enum UserGroupStatus {
  WAITING,
  ACCEPTED,
  REJECTED,
}

export interface Group {
  groupId?: number;
  groupName?: string;
  groupOwner?: string;
  memberCount?: number;
  createdAt?: string;
  status?: UserGroupStatus;
}
