import Cookies from "js-cookie";

const defaultHeaders = () => {
  const accessToken: string | undefined = Cookies.get("acstk");
  return {
    "Content-Type": "application/json",
    Authorization: `Bearer ${accessToken}`,
  };
};

export interface ApiError {
  message?: string;
}

const callGet = async (url: string): Promise<any> => {
  console.log(`[call api] get - ${url}`);
  const options = {
    headers: defaultHeaders(),
    method: "GET",
  };
  const response = await fetch(url, options);
  return await handleResponse(url, response);
};

const callPost = async (url: string, requestData: any = {}): Promise<any> => {
  console.log(`[call api] post - ${url}, requestData: `, requestData);
  const options = {
    headers: defaultHeaders(),
    method: "POST",
    body: JSON.stringify(requestData),
  };
  const response = await fetch(url, options);
  return await handleResponse(url, response);
};

const callPut = async (url: string, requestData: any = {}): Promise<any> => {
  console.log(`[call api] put - ${url}, requestData: `, requestData);
  const options = {
    headers: defaultHeaders(),
    method: "PUT",
    body: JSON.stringify(requestData),
  };
  const response = await fetch(url, options);
  return await handleResponse(url, response);
};

const callDelete = async (url: string): Promise<any> => {
  console.log(`[call api] delete - ${url}`);
  const options = {
    headers: defaultHeaders(),
    method: "DELETE",
  };
  const response = await fetch(url, options);
  return await handleResponse(url, response);
};

const handleResponse = async (url: string, response: Response) => {
  let body = {};
  try {
    body = await response.json();
  } catch (e) {
    console.warn("json parsing error", e);
  }

  if (response.status !== 200) {
    console.warn(
      `[call api] - ${url}, Error! status: ${response.status}, body: `,
      body
    );
    throw body;
  } else {
    console.info(
      `[call api] - ${url}, Success! status: ${response.status}, body: `,
      body
    );
    return body;
  }
};

const hasResponseBody = (response: Response): boolean => {
  const contentLength = Number(response.headers.get("content-length") ?? "0");
  return contentLength > 0;
};

export { callGet, callPost, callPut, callDelete };
