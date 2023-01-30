const defaultOptions = {
  headers: {
    "Content-Type": "application/json",
  },
};

export interface ApiError {
  message?: string;
}

const handleResponse = async (response: Response) => {
  if (response.status !== 200) {
    throw await response.json();
  } else {
    return await response.json();
  }
};

const callGet = async (url: string): Promise<any> => {
  console.log(`[call api] get - ${url}`);
  const options = {
    method: "GET",
  };
  const response = await fetch(url, options);
  return await handleResponse(response);
};

const callPost = async (url: string, requestData: any = {}): Promise<any> => {
  console.log(`[call api] post - ${url}`);
  const options = {
    ...defaultOptions,
    method: "POST",
    body: JSON.stringify(requestData),
  };
  const response = await fetch(url, options);
  return await handleResponse(response);
};

const callPut = async (url: string, requestData: any = {}): Promise<any> => {
  console.log(`[call api] put - ${url}`);
  const options = {
    ...defaultOptions,
    method: "PUT",
    body: JSON.stringify(requestData),
  };
  const response = await fetch(url, options);
  return await handleResponse(response);
};

const callDelete = async (url: string): Promise<any> => {
  console.log(`[call api] delete - ${url}`);
  const options = {
    method: "DELETE",
  };
  const response = await fetch(url, options);
  return await handleResponse(response);
};

export { callGet, callPost, callPut, callDelete };
