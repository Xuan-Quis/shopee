import React, { Children } from "react";

interface Props {
  children?: React.ReactNode;
  style?: React.CSSProperties;
  handleOnClick?: (event: React.MouseEvent<HTMLButtonElement>) => void;
}

const ButtonComponent: React.FC<Props> = ({
  children,
  style,
  handleOnClick,
}) => {
  return (
    <div>
      <button
        onClick={handleOnClick}
        className="py-2 text-white flex items-center gap-2 justify-center w-full"
        style={style}
      >
        {children}
      </button>
    </div>
  );
};

export default ButtonComponent;
