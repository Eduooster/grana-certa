import { ScreenContainer } from "@/app/components/layouts/ScreenContainer";
import { AnalysisSummaryCard } from "./AnalysisSummaryCard";
import { BeforeSpendDetailsHeader } from "./BeforeSpendDetailsHeader";
import { ImpactFactorsCard } from "./ImpactFactorsCard";
import { ProjectionChartCard } from "./ProjectionChartCard";
import { UpcomingExpensesList } from "./UpcomingExpensesList";

export function BeforeSpendDeatilsScreen() {
  return (
     <ScreenContainer>
       
       <BeforeSpendDetailsHeader/>
       
        <AnalysisSummaryCard />

        
        <ImpactFactorsCard />

       
        <ProjectionChartCard />

   
        <UpcomingExpensesList />
      </ScreenContainer>
  );
}