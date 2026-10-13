import { useState } from 'react';
import { View, Text } from 'react-native';
import { useRouter } from 'expo-router';
import { SimulationResult, mockSimulation, upcomingTransactions } from '../beforeSpend.mock';
import { SimulationInput } from './SimulationInput';
import { SimulationResultCard } from './SimulationResultCard';
import { UpcomingTransactions } from './UpcomingTransactions';
import { Feather } from '@expo/vector-icons';

export function BeforeSpend() {
  const router = useRouter();

  const [amount, setAmount] = useState('');
  const [result, setResult] = useState<SimulationResult | null>(null);

  const handleSimulate = () => {
    const numericAmount = Number(amount.replace(/\./g, '').replace(',', '.'));

    if (!numericAmount || numericAmount <= 0) {
      return;
    }

    setResult(mockSimulation(numericAmount));
  };

  const handleViewDetails = () => {
    if (!result) return;

    router.push({
      pathname: '/planning/before-spend-details',
      params: {
        amount,
        status: result.status,
        availableAfter: result.availableAfter,
        marginImpact: result.marginImpact,
        analysis: result.analysis,
      },
    });
  };

  return (
    <View className="w-full">
      <View className="mb-4 mt-1">
        <View className="mb-1.5 flex-row items-center">
          <View className="mr-2 h-8 w-8 items-center justify-center rounded-xl bg-violet-100">
            <Feather name="activity" size={16} color="#7650D8" strokeWidth={1.8} />
          </View>
          <Text className="text-[19px] font-bold tracking-tight text-slate-900">
            Antes de gastar
          </Text>
        </View>

        <Text className="mt-1 text-sm leading-5 text-slate-500">
          Simule uma despesa e veja o impacto no orçamento antes de decidir.
        </Text>
      </View>

      <SimulationInput amount={amount} onChangeAmount={setAmount} onSimulate={handleSimulate} />

      {result && <SimulationResultCard result={result} onViewDetails={handleViewDetails} />}

      <UpcomingTransactions transactions={upcomingTransactions} />
    </View>
  );
}
