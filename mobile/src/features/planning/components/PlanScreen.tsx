import { ScreenContainer } from '@/app/components/layouts/ScreenContainer';
import AvailableBalanceCard from './AvailableBalanceCard';
import { BeforeSpend } from './BeforeSpend';
import { Feather } from '@expo/vector-icons';
import { Text, View } from 'react-native';

export function PlanScreen() {
  return (
    <ScreenContainer flowBackground>
      <View className="mb-2 flex-row items-center justify-between">
        <View className="flex-1 pr-3">
          <Text className="text-xs font-semibold uppercase tracking-[1.5px] text-violet-600">
            Planejamento
          </Text>
          <Text className="mt-1 text-[26px] font-bold leading-8 tracking-tight text-slate-900">
            Seu dinheiro, com direção
          </Text>
          <Text className="mt-1 text-sm leading-5 text-slate-500">
            Organize os próximos passos com clareza.
          </Text>
        </View>
        <View
          className="h-12 w-12 items-center justify-center rounded-2xl border border-violet-100 bg-white"
          style={{
            shadowColor: '#35245B',
            shadowOffset: { width: 0, height: 5 },
            shadowOpacity: 0.07,
            shadowRadius: 12,
            elevation: 2,
          }}>
          <Feather name="calendar" size={21} color="#7650D8" strokeWidth={1.8} />
        </View>
      </View>
      <AvailableBalanceCard />
      <BeforeSpend />
    </ScreenContainer>
  );
}
