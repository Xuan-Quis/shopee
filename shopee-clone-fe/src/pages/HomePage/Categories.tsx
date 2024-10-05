import { useQuery } from "@tanstack/react-query";

import CategorySwiper from "./CategorySwiper";
import { getCategories } from "../../api/categories";

const Categories = () => {
  const { isLoading, error, data } = useQuery({
    queryKey: ["categories"],
    queryFn: () => getCategories(),
  });
  if (isLoading) return <p>Đang tải ...</p>;
  if (error) return <p>Không có dữ liệu về categories</p>;

  return (
    <div style={{ background: "white" }} className="px-40">
      <h3 className="p-5 text-[#0000008A]">Danh mục</h3>
      <CategorySwiper data={data.body} />
    </div>
  );
};

export default Categories;
