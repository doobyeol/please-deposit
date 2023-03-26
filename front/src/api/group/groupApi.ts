import type { Group, UserGroupStatus } from "@/models/group/Group";
import { callGet, callPost, callPut } from "../http";

export const getGroupList = async (): Promise<Array<Group>> => {
  const data: Array<Group> = await callGet("/api/group/user/list");
  return data;
};

export const updateUserGroupStatus = async (
  groupId: number,
  status: UserGroupStatus
): Promise<Array<Group>> => {
  const data: Array<Group> = await callPut(
    `/api/group/${groupId}/status/${status}`
  );
  return data;
};

export const createGroup = async (groupName: string): Promise<Group> => {
  return await callPost(`/api/group`, { groupName });
};
