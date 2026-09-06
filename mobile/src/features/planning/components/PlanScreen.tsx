import { ScreenContainer } from "@/app/components/layouts/ScreenContainer";
import { ScreenHeader } from "@/app/components/ScreenHeader";
import AvailableBalanceCard from "./AvailableBalanceCard";
import { BeforeSpend } from "./BeforeSpend";

export function PlanScreen() {
  return (
    <ScreenContainer>
       <ScreenHeader title={"Crie seus planejamentos"}/>
       <AvailableBalanceCard/>
       <BeforeSpend/>
     </ScreenContainer>
  );
}