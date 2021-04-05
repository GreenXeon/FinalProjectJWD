package by.epam.jwd.finalproj.strategy;

import by.epam.jwd.finalproj.strategy.impl.CreditPayStrategy;
import by.epam.jwd.finalproj.strategy.impl.OnlinePayStrategy;

public enum StrategyManager {
    ONLINE(new OnlinePayStrategy()),
    CREDIT(new CreditPayStrategy());

    private final CommonStrategy strategy;

    StrategyManager(CommonStrategy Strategy) {
        this.strategy = Strategy;
    }

    public static CommonStrategy findStrategyByName(String name){
        for(StrategyManager strategy : StrategyManager.values()){
            if (strategy.name().equalsIgnoreCase(name)){
                return strategy.strategy;
            }
        }
        return CREDIT.strategy;
    }
}
