import type { Group } from "@/models/group/Group";
import { callGet, callPost } from "../http";

const getGroupList = async (): Promise<Array<Group>> => {
  const data: Array<Group> = await callGet("/api/group/user/list");
  return data;
};

export { getGroupList };
