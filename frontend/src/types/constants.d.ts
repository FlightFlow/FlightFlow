import { ReactNode } from "react";

namespace ConstantTypes {
  export interface SidebarSectionProps {
    sectionKey: number | string;
    sectionName: string;
    elements: {
      elementKey: number | string;
      elementIcon: ReactNode;
      elementText: string;
      elementPath: string;
    }[];
  }
}

export default ConstantTypes;
