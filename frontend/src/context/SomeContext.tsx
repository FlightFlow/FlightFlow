import { createContext, FC, ReactNode, useState } from "react";

interface contextProps {
  state: boolean;
  setState: (state: boolean) => void;
}

export const someContext = createContext<contextProps>({
  state: true,
  setState: () => {},
});

export const SomeContextProvider: FC<{ children: ReactNode }> = ({ children }) => {
  const [state, setState] = useState<boolean>(true);

  return <someContext.Provider value={{ state, setState }}>{children}</someContext.Provider>;
};

export default SomeContextProvider;
