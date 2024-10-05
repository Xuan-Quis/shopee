## Cài các thư viện cần thiết

`npm install`

## Chạy dự án

`npm start`

Ai rảnh cài thêm `vite` chạy cho nhanh

## Chú ý về cấu trúc file

Folder `Pages` chứa các trang
Các thành phần của trang nằm trong `components`

## Categories

Trong thư mục _api_ mình có tạo file _categories.ts_ để call api với axios
Gợi ý call api dùng React Query

```
const { data, isLoading, error } = useQuery({
    queryKey: ["categories"],
    queryFn: () => getCategories(),
    staleTime: 5 * 60 * 1000, // 5 minutes
  });
```

## Đăng nhập

Thư viện mình dùng RTK, Axios, ReactQuery
Flow :

- Người dùng Click _Đăng nhập_ => kích hoạt _loginMutation_
- _LoginMutation_ tiến hành gọi API bằng _axios_
- Dữ liệu trả về thành công => dispatch `checkIsAuthenticated(true)`
- Lúc này globalState _isAuthenticated_ về true
- => đã đăng nhập
- `dispatch(addUser(data))` -> lưu user trả về vào global state `user`

## Vấn đề của xử lý đăng nhập

Còn 1 cách khác thay vì dùng _ReactQuery_ ta dùng _RTK query_

Trên đây là xử lý khi dữ liệu không trả về _refreshToken_ hay _accessToken_

-> Lúc đấy mình sẽ nói thêm :smile:

Đăng ký thì cũng tương tự thui
