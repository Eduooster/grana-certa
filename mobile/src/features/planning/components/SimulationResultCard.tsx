import { View, Text, Pressable } from 'react-native';
import { Feather } from '@expo/vector-icons';

import { colors } from '@/constants/colors';
import { SimulationResult } from '../beforeSpend.mock';

type SimulationResultCardProps = {
  result: SimulationResult;
  onViewDetails: () => void;
};

export function SimulationResultCard({ result, onViewDetails }: SimulationResultCardProps) {
  const getStatusConfig = () => {
    switch (result.status) {
      case 'safe':
        return {
          icon: 'check-circle' as const,
          title: 'Esse gasto cabe no orçamento',
          iconColor: colors.success,
          background: 'bg-emerald-50',
        };

      case 'attention':
        return {
          icon: 'alert-circle' as const,
          title: 'Atenção ao impacto',
          iconColor: '#F59E0B',
          background: 'bg-amber-50',
        };

      case 'danger':
        return {
          icon: 'x-circle' as const,
          title: 'Esse gasto não é recomendado',
          iconColor: '#EF4444',
          background: 'bg-red-50',
        };
    }
  };

  const statusConfig = getStatusConfig();

  return (
    <View
      className="mb-4 overflow-hidden rounded-[22px] border border-white bg-white"
      style={{
        shadowColor: '#35245B',
        shadowOffset: { width: 0, height: 7 },
        shadowOpacity: 0.06,
        shadowRadius: 16,
        elevation: 2,
      }}>
      <View className={`px-4 py-4 ${statusConfig.background}`}>
        <View className="flex-row items-center">
          <Feather
            name={statusConfig.icon as any}
            size={21}
            color={statusConfig.iconColor}
            strokeWidth={1.8}
          />

          <Text className="ml-2 flex-1 text-sm font-bold text-slate-900">{statusConfig.title}</Text>
        </View>
      </View>

      <View className="px-4 py-4">
        <View className="flex-row">
          <View className="flex-1">
            <Text className="text-xs text-slate-500">Disponível após o gasto</Text>

            <Text className="mt-1 text-lg font-bold text-slate-900">{result.availableAfter}</Text>
          </View>

          <View className="w-[1px] bg-slate-100" />

          <View className="flex-1 pl-4">
            <Text className="text-xs text-slate-500">Impacto na margem</Text>

            <Text className="mt-1 text-lg font-bold text-slate-900">{result.marginImpact}</Text>
          </View>
        </View>

        <View className="mt-4 border-t border-slate-100 pt-4">
          <View className="mb-2 flex-row items-center">
            <Feather name="zap" size={16} color={colors.primary} strokeWidth={1.8} />

            <Text className="ml-2 text-xs font-semibold text-slate-500">Análise</Text>
          </View>

          <Text className="text-sm leading-5 text-slate-600">{result.analysis}</Text>

          <Pressable
            onPress={onViewDetails}
            className="mt-4 flex-row items-center justify-center rounded-xl border border-slate-200 py-3 active:opacity-70">
            <Text className="text-sm font-semibold text-slate-700">Ver detalhes</Text>

            <Feather
              name="chevron-right"
              size={16}
              color="#475569"
              style={{ marginLeft: 4 }}
              strokeWidth={1.8}
            />
          </Pressable>
        </View>
      </View>
    </View>
  );
}
